import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { PdgComponent } from './pdg/pdg.component';
import { AjoutuserComponent } from './ajoutuser/ajoutuser.component';
import { ModificationuserComponent } from './modificationuser/modificationuser.component';
import { VendeursComponent } from './vendeurs/vendeurs.component';
import { ArticlesComponent } from './articles/articles.component';
import { CdmajoutComponent } from './cdmajout/cdmajout.component';
import { AuthGaurdService } from './service/auth-gaurd.service';
import { LogoutComponent } from './logout/logout.component';
import { ArticleAjoutComponent } from './article-ajout/article-ajout.component';
import { ArticleModifComponent } from './article-modif/article-modif.component';
import { VendeurAjoutComponent } from './vendeur-ajout/vendeur-ajout.component';
import { VendeurModifComponent } from './vendeur-modif/vendeur-modif.component';


const routes: Routes = [
  {
    path: '',
    redirectTo: '/articles',
    pathMatch: 'full'
  }, {
    path: 'login',
    component: LoginComponent
  }, {
    path: 'logout',
    component: LogoutComponent,
    canActivate: [AuthGaurdService],
  }, {
    path: 'articles',
    component: ArticlesComponent,
    canActivate: [AuthGaurdService],
  }, {
    path: 'article-ajout',
    component: ArticleAjoutComponent,
    canActivate: [AuthGaurdService],
  }, {
    path: 'article-modif',
    component: ArticleModifComponent,
    canActivate: [AuthGaurdService],
  }, {
    path: 'vendeurs',
    component: VendeursComponent,
    canActivate: [AuthGaurdService],
  }, {
    path: 'vendeur-ajout',
    component: VendeurAjoutComponent,
    canActivate: [AuthGaurdService],
  }, {
    path: 'vendeur-modif',
    component: VendeurModifComponent,
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
    path: 'chefmagasins',
    component: PdgComponent,
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
