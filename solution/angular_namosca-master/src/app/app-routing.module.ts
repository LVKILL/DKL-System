import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ReservaFormComponent } from './view/reserva/reserva-form/reserva-form.component';
import { ReservaListComponent } from './view/reserva/reserva-list/reserva-list.component';
import { HomeComponent } from './view/home/home.component';


const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'reserva',
      children: [
        {path: ':reserva_id',  component: ReservaFormComponent },
        {path: '', component: ReservaFormComponent }
      ]
  },
  {
    path: 'reserva-list',
    component: ReservaListComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
