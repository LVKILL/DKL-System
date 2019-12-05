import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ReservaFormComponent } from './view/reserva/reserva-form/reserva-form.component';


const routes: Routes = [
  {
    path: 'reserva',
    component: ReservaFormComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
