import { Component, OnInit } from '@angular/core';
import { ClienteService } from 'src/app/service/cliente.service';
import { AmbienteService } from 'src/app/service/ambiente.service';
import { ArmaService } from 'src/app/service/arma.service';

@Component({
  selector: 'app-reserva-form',
  templateUrl: './reserva-form.component.html',
  styleUrls: ['./reserva-form.component.css']
})
export class ReservaFormComponent implements OnInit {

  constructor(
    private clienteService: ClienteService,
    private ambienteService: AmbienteService,
    private armaService: ArmaService
    ) { }

  clientes: any[];
  ambientes: any[];
  armas: any[];

  ngOnInit() {
    this.bindClients();
    this.bindAmbiente();
    this.bindArma();
  }
  
  /**
   * Carrega objeto clientes no options Clientes
   */
  bindClients(): void  {
    this.clienteService.getClients().subscribe(data => {
      this.clientes = data.content;
    });
  }

  /**
   * carrega objeto arma no options armas
   */
  bindAmbiente(): void {
    this.ambienteService.getAmbientes().subscribe(data => {
      this.ambientes = data.content;
    });
  }

  bindArma(): void {
    this.armaService.getArmas().subscribe(data => {
      console.log(data);
      this.armas = data.content;
    });
  }
}
