import { Component, OnInit } from '@angular/core';
import { ReservaService } from 'src/app/service/reserva.service';


@Component({
  selector: 'app-reserva-list',
  templateUrl: './reserva-list.component.html',
  styleUrls: ['./reserva-list.component.css']
})
export class ReservaListComponent implements OnInit {

  constructor(
    private reservaService: ReservaService,
  ) { }

  reservas: any[];

  ngOnInit(
  ) {
    this.bindReserva();
  }

  bindReserva(): void {
    this.reservaService.listarReserva().subscribe(data => {
      console.log(data);
      this.reservas = data.content;
    });
  }

}
