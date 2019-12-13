import { Component, OnInit } from '@angular/core';
import { ClienteService } from 'src/app/service/cliente.service';
import { AmbienteService } from 'src/app/service/ambiente.service';
import { ArmaService } from 'src/app/service/arma.service';
import { FormBuilder, FormGroup, FormControl } from '@angular/forms';
import { ReservaService } from 'src/app/service/reserva.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-reserva-form',
  templateUrl: './reserva-form.component.html',
  styleUrls: ['./reserva-form.component.css']
})
export class ReservaFormComponent implements OnInit {

  constructor(
    private clienteService: ClienteService,
    private ambienteService: AmbienteService,
    private armaService: ArmaService,
    private reservaService: ReservaService, 
    private router: Router,
    private route: ActivatedRoute
    ) { }

  /**
   * variavaveis
   * https://stackoverflow.com/questions/52364998/get-data-from-url-in-angular-5
   */

  clientes: any[];
  ambientes: any[];
  reserva_id;
  armas: any[];
  armaSelecionada: any;
  armasSelecionadas: {
    id: number,
    descricao:string,
    calibre: string,
    quantidade: number
  }[] = new Array();
  reserva: any = new Object;
  formArma:FormGroup = new FormGroup({
    arma: new FormControl('arma')
  });
  formReserva:FormGroup = new FormGroup({
    id: new FormControl('id'),
    cliente: new FormControl('cliente'),
    ambiente: new FormControl('ambiente'),
    dataHoraInicio: new FormControl('dataHoraInicio'),
    dataHoraFim: new FormControl('dataHoraFim')
  });


  ngOnInit() {
    this.bindClients();
    this.bindAmbiente();
    this.bindArma();
    this.reserva_id = this.route.snapshot.paramMap.get("reserva_id");
    if(this.reserva_id !== null){
       this.bindFormData();
    }else {
       this.ff.id.value == "";
    }
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
      this.armas = data.content;
    });
  }

  /**
   * Carrega formulário de Editar 
   */
  bindFormData(): void {
    this.reservaService.listaReservaPorId(this.reserva_id).subscribe( data => {
      console.log(data);
      this.formReserva.setValue({
          id: this.reserva_id,
          cliente: data.cliente.id,
          ambiente: data.ambiente.id,
          dataHoraInicio: data.inicioDaLocacao,
          dataHoraFim: data.fimDaLocacao
      });
      for (let i = 0; i < data.armaLocadas.length; i++){
        this.armasSelecionadas.push(data.armaLocadas[i].arma);
      }
    });
  }

  public reservar(){
    let armasReservadas = new Array();
    for(let i = 0; i< this.armasSelecionadas.length; i++){
      armasReservadas.push({id: this.armasSelecionadas[i].id, quantidade: 1});
    }

    console.log(this.ff.id.value);
    this.reservaService.salvaReserva(
      (this.ff.id.value === "id" ? null : this.ff.id.value),
      this.ff.cliente.value,
      this.ff.ambiente.value,
      this.ff.dataHoraInicio.value,
      this.ff.dataHoraFim.value,
      armasReservadas
    ).subscribe( data => {
       this.router.navigate(['/reserva-list'])
    }, error => {
       this.router.navigate(['/reserva-list'])
    });
  }

  adicionaArma(): void {
    this.armasSelecionadas.push(this.f.arma.value);
    console.log(this.armasSelecionadas);
  }

  removeArma(index): void {
     console.log("Chegou aqui");
     console.log(index);
     this.armasSelecionadas.splice(index, 1);
  }

  get f() {
    return this.formArma.controls;
  }
  get ff(){
    return this.formReserva.controls;
  }
}
