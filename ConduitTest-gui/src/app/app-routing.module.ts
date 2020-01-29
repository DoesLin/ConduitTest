import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { PdgComponent } from './pdg/pdg.component';
import { AjoutuserComponent } from './ajoutuser/ajoutuser.component';
import { ModificationuserComponent } from './modificationuser/modificationuser.component';
import { CdmComponent } from './cdm/cdm.component';
import { VendeurComponent } from './vendeur/vendeur.component';
import { ArticleComponent } from './article/article.component';
import { CdmajoutComponent } from './cdmajout/cdmajout.component';
import { AuthGaurdService } from './service/auth-gaurd.service';


const routes: Routes = [
  {
    path: '',
    redirectTo: '/article',
    pathMatch: 'full'
  }, {
    path: 'login',
    component: LoginComponent
  }, {
    path: 'pdg',
    component: PdgComponent,
    canActivate: [AuthGaurdService],
  }, {
    path: 'ajoutuser',
    component: AjoutuserComponent,
    canActivate: [AuthGaurdService],
  }, {
    path: 'modificationuser',
    component: ModificationuserComponent,
    canActivate: [AuthGaurdService],
  }, {
    path: 'cdm',
    component: CdmComponent,
    canActivate: [AuthGaurdService],
  }, {
    path: 'vendeur',
    component: VendeurComponent,
    canActivate: [AuthGaurdService],
  }, {
    path: 'article',
    component: ArticleComponent,
    canActivate: [AuthGaurdService],
  }, {
    path: 'cdmajout',
    component: CdmajoutComponent,
    canActivate: [AuthGaurdService],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { enableTracing: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
