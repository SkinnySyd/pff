import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProviderDto } from '../components/add-server/add-server.component';

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
export interface ServerDto {
  id?: number; // Optional if not always provided
  name: string;
  ipAdd: string;
  login: string;
  password: string;
  provider: {name: string}; 
  }

@Injectable({
  providedIn: 'root'
})
export class ServerService {
  private baseUrl = 'http://localhost:8080/api/servers'; // Change this to your backend URL

  constructor(private http: HttpClient) {}

  getServers(): Observable<Server[]> {
    return this.http.get<Server[]>(this.baseUrl+"/all");
  }

  addServer(server: ServerDto): Observable<Server> {
    return this.http.post<Server>(this.baseUrl+"/create", server);
  }

  deleteServer(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/delete/${id}`);
  }
}
