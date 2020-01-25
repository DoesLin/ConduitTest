import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
// import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { PdgComponent } from './pdg/pdg.component';
import { AjoutuserComponent } from './ajoutuser/ajoutuser.component';
import { ModificationuserComponent} from './modificationuser/modificationuser.component';
import { CdmComponent} from './cdm/cdm.component';
import { VendeurComponent} from './vendeur/vendeur.component';
import { ArticleComponent} from './article/article.component';
import { CdmajoutComponent} from './cdmajout/cdmajout.component';




// import { AjoutcdmComponent} from './ajoutcdm/ajoutcdm.component';
// import { ModificationcdmComponent} from './modificationcdm/modificationcdm.component';

// import { AjoutatlComponent } from './ajoutatl/ajoutatl.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';



@NgModule({

  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    HomeComponent,
    LoginComponent,
    PdgComponent,
    AjoutuserComponent,
    ModificationuserComponent,
    CdmComponent,
    VendeurComponent,
    ArticleComponent,
    CdmajoutComponent,
    // AjoutcdmComponent,
    // ModificationcdmComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    // NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

