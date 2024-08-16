import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';

interface Provider {
  id: number;
  name: string;
}

@Injectable({
  providedIn: 'root'
})
export class ProviderService {
  private baseUrl = 'http://localhost:8080/providers'; // Change this to your backend URL

  constructor(private http: HttpClient) {}

  getProviders(): Observable<Provider[]> {
    return this.http.get<Provider[]>(this.baseUrl+"/all");
  }

  // Method to get all provider names by mapping from the full provider list
  getProviderNames(): Observable<string[]> {
    return this.getProviders().pipe(
      map((providers: Provider[]) => providers.map(provider => provider.name))
    );
  }

  // Method to get a provider by name
  getProviderByName(name: string): Observable<Provider> {
    return this.http.post<Provider>(`${this.baseUrl}/name`, { name });
  }



  addProvider(provider: Provider): Observable<Provider> {
    return this.http.post<Provider>(this.baseUrl+"/create", provider);
  }

  deleteProvider(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/delete/${id}`);
  }
}
