import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent} from './login/login.component';
import { PdgComponent} from './pdg/pdg.component';


const routes: Routes = [
    {
        path: 'home', 
        component: HomeComponent
    },{ 
        path: '',
        redirectTo: '/home',
        pathMatch: 'full'
    },{
      path : 'login',
      component:LoginComponent
    },{
      path : 'pdg',
      component:PdgComponent
    },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { enableTracing: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
