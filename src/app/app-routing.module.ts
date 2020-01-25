import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent} from './login/login.component';
import { PdgComponent} from './pdg/pdg.component';
import { AjoutuserComponent} from './ajoutuser/ajoutuser.component';
import { ModificationuserComponent} from './modificationuser/modificationuser.component';
import { CdmComponent} from './cdm/cdm.component';
import { VendeurComponent} from './vendeur/vendeur.component';
import { ArticleComponent} from './article/article.component';
import { CdmajoutComponent} from './cdmajout/cdmajout.component';

// import { AjoutcdmComponent} from './ajoutcdm/ajoutcdm.component';
// import { ModificationcdmComponent} from './modificationcdm/modificationcdm.component';


// import { AjoutatlComponent } from './ajoutatl/ajoutatl.component';



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
    },{
      path : 'ajoutuser',
      component:AjoutuserComponent
    },{
      path : 'modificationuser',
      component: ModificationuserComponent
    },{
      path : 'cdm',
      component: CdmComponent
    },{
      path:'vendeur',
      component :VendeurComponent
    },{
      path:'article',
      component :ArticleComponent
    },{
      path:'cdmajout',
      component :CdmajoutComponent
    },
    // {
    //   path : 'ajouteatl',
    //   component : AjoutatlComponent
    // },
    // {
    //   path : 'ajoutcdm',
    //   component : AjoutcdmComponent
    // },
    // {
    //   path : 'modificationcdm',
    //   component : ModificationcdmComponent
    // },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { enableTracing: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
