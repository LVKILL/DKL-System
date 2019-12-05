import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ArmaService {

  constructor(
    private http : HttpClient
  ) { }
  /**
   * Retorna armas
   */
  public getArmas () {
    return this.http.get<any>('http://127.0.0.1:9090/api/armas?busca=&pagina=0&tamanho=20');
  };

}
