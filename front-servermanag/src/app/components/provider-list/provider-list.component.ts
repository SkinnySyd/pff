import { Component, OnInit } from '@angular/core';
import { ProviderService } from '../../services/provider.service';
import { Observable } from 'rxjs';

interface Provider {
  id: number;
  name: string;
}

@Component({
  selector: 'app-provider-list',
  templateUrl: './provider-list.component.html',
  styleUrls: ['./provider-list.component.css']
})
export class ProviderListComponent implements OnInit {
  providers: Provider[] = [];
  newProvider: string = '';
  searchTerm: string = ''; // Added search term property

  constructor(private providerService: ProviderService) {}

  ngOnInit(): void {
    this.loadProviders();
  }

  loadProviders(): void {
    this.providerService.getProviders().subscribe((data) => {
      this.providers = data;
    });
  }

  addProvider(): void {
    if (this.newProvider.trim()) {
      const newProviderObj: Provider = { id: 0, name: this.newProvider };
      this.providerService.addProvider(newProviderObj).subscribe(() => {
        this.loadProviders();
        this.newProvider = '';
      });
    }
  }

  deleteProvider(id: number): void {
    this.providerService.deleteProvider(id).subscribe(() => {
      this.loadProviders();
    });
  }

  // Filter providers based on search term
  filteredProviders(): Provider[] {
    if (!this.searchTerm) {
      return this.providers;
    }
    return this.providers.filter(provider =>
      provider.name.toLowerCase().includes(this.searchTerm.toLowerCase()) || 
      provider.id.toString().includes(this.searchTerm.toLowerCase())
      
    );
  }
}
