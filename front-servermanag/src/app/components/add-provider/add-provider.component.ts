import { Component } from '@angular/core';
import { ProviderService } from '../../services/provider.service';

interface Provider {
  id: number;
  name: string;
}

@Component({
  selector: 'app-add-provider',
  templateUrl: './add-provider.component.html',
  styleUrls: ['./add-provider.component.css']
})
export class AddProviderComponent {
  newProvider: string = '';
  showNotification: boolean = false;

  constructor(private providerService: ProviderService) {}

  addProvider(): void {
    if (this.newProvider.trim()) {
      const newProviderObj: Provider = { id: 0, name: this.newProvider };
      this.providerService.addProvider(newProviderObj).subscribe(() => {
        this.newProvider = '';
        this.showNotification = true;
        setTimeout(() => {
          this.showNotification = false;
        }, 60000); // Adjust the duration as needed
      });
    }
  }
}

