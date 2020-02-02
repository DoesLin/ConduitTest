import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { VendeursComponent } from './vendeurs/vendeurs.component';
import { ArticlesComponent } from './articles/articles.component';
import { AuthGaurdService } from './service/authentication/auth-gaurd.service';
import { LogoutComponent } from './logout/logout.component';
import { ArticleAjoutComponent } from './article-ajout/article-ajout.component';
import { ArticleModifComponent } from './article-modif/article-modif.component';
import { VendeurAjoutComponent } from './vendeur-ajout/vendeur-ajout.component';
import { VendeurModifComponent } from './vendeur-modif/vendeur-modif.component';
import { AuthGaurdChefmagasinService } from './service/authentication/auth-gaurd-chefmagasin.service';
import { ChefmagasinsComponent } from './chefmagasins/chefmagasins.component';
import { ChefmagasinAjoutComponent } from './chefmagasin-ajout/chefmagasin-ajout.component';
import { ChefmagasinModifComponent } from './chefmagasin-modif/chefmagasin-modif.component';


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
    canActivate: [AuthGaurdChefmagasinService],
  }, {
    path: 'vendeur-ajout',
    component: VendeurAjoutComponent,
    canActivate: [AuthGaurdChefmagasinService],
  }, {
    path: 'vendeur-modif',
    component: VendeurModifComponent,
    canActivate: [AuthGaurdChefmagasinService],
  }, {
    path: 'chefmagasins',
    component: ChefmagasinsComponent,
    canActivate: [AuthGaurdChefmagasinService],
  }, {
    path: 'chefmagasin-ajout',
    component: ChefmagasinAjoutComponent,
    canActivate: [AuthGaurdChefmagasinService],
  }, {
    path: 'chefmagasin-modif',
    component: ChefmagasinModifComponent,
    canActivate: [AuthGaurdChefmagasinService],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { enableTracing: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
