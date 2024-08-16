import { Component, OnInit } from '@angular/core';
import { ServerService } from '../../services/server.service';

interface Provider {
  id: number;
  name: string;
}

interface Server {
  id: number;
  name: string;
  ipAdd: string;
  // login: string;
  // password: string;
  provider: Provider;
}

@Component({
  selector: 'app-server-list',
  templateUrl: './server-list.component.html',
  styleUrls: ['./server-list.component.css']
})
export class ServerListComponent implements OnInit {
  servers: Server[] = [];
  filterText: string = '';
  showConfirmation: boolean = false;
  serverToDelete?: Server;

  constructor(private serverService: ServerService) {}

  ngOnInit(): void {
    this.loadServers();
  }

  loadServers(): void {
    this.serverService.getServers().subscribe((data) => {
      console.log('Servers data:', data); // Log the data to check its structure
      this.servers = data;
    });
  }

  // deleteServer(id: number): void {
  //   if (confirm('Are you sure you want to delete this server?')) {
  //     this.serverService.deleteServer(id).subscribe(() => {
  //       this.loadServers(); 
  //     });
  //   }
  // }
  deleteServer(id: number): void {
    const server = this.servers.find(s => s.id === id);
    if (server) {
      this.serverToDelete = server;
      this.showConfirmation = true;
    }
  }

  confirmDelete(): void {
    if (this.serverToDelete) {
      this.serverService.deleteServer(this.serverToDelete.id).subscribe(() => {
        this.loadServers();
        this.showConfirmation = false;
        this.serverToDelete = undefined;
      });
    }
  }

  cancelDelete(): void {
    this.showConfirmation = false;
    this.serverToDelete = undefined;
  }

  

  get filteredServers(): Server[] {
    return this.servers.filter(server =>
      server.name.toLowerCase().includes(this.filterText.toLowerCase()) ||
      server.ipAdd.includes(this.filterText) ||
      server.provider.name.toLowerCase().includes(this.filterText.toLowerCase())
    );
  }
}