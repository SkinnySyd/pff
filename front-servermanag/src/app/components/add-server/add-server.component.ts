import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ServerService } from '../../services/server.service';
import { ProviderService } from '../../services/provider.service';
// import { ProviderDto } from '../../provider.dto';

export interface ProviderDto {
  id: number;
  name: string;
}

@Component({
  selector: 'app-add-server',
  templateUrl: './add-server.component.html',
  styleUrls: ['./add-server.component.css']
})
export class AddServerComponent implements OnInit {
  serverForm: FormGroup;
  providers: ProviderDto[] = []; // Define the type here

  constructor(
    private fb: FormBuilder,
    private serverService: ServerService,
    private providerService: ProviderService
  ) {
    this.serverForm = this.fb.group({
      name: ['', Validators.required],
      ipAdd: ['', Validators.required],
      login: ['', Validators.required],
      password: ['', Validators.required],
      provider: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.loadProviders();
  }

  loadProviders(): void {
    this.providerService.getProviders().subscribe((providers: ProviderDto[]) => {
      this.providers = providers;
    });
  }

  onSubmit(): void {
    if (this.serverForm.valid) {
      // Create a server DTO object with the correct format
      const serverDto = {
        name: this.serverForm.get('name')?.value,
        ipAdd: this.serverForm.get('ipAdd')?.value,
        login: this.serverForm.get('login')?.value,
        password: this.serverForm.get('password')?.value,
        provider: { name: this.serverForm.get('provider')?.value } // Wrap the ID in an object
      };
  
      this.serverService.addServer(serverDto).subscribe(() => {
        alert('Server added successfully!');
        this.serverForm.reset();
      });
    }
  }
  
}
