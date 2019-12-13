import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AmbienteService {

  constructor(
    private http : HttpClient
  ) { }
  /**
   * Retorna ambientes
   */
  public getAmbientes () {
    return this.http.get<any>('http://127.0.0.1:9090/api/ambientes?busca=&pagina=0&tamanho=20');
  };
}
