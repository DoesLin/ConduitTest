import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { LoginComponent } from './login/login.component';
import { PdgComponent } from './pdg/pdg.component';
import { AjoutuserComponent } from './ajoutuser/ajoutuser.component';
import { ModificationuserComponent } from './modificationuser/modificationuser.component';
import { VendeursComponent } from './vendeurs/vendeurs.component';
import { ArticlesComponent } from './articles/articles.component';
import { CdmajoutComponent } from './cdmajout/cdmajout.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LogoutComponent } from './logout/logout.component';
import { BasicAuthHtppInterceptorService } from './service/basic-auth-htpp-interceptor.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ArticleAjoutComponent } from './article-ajout/article-ajout.component';
import { ArticleModifComponent } from './article-modif/article-modif.component';
import { VendeurAjoutComponent } from './vendeur-ajout/vendeur-ajout.component';
import { VendeurModifComponent } from './vendeur-modif/vendeur-modif.component';



@NgModule({

  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    LoginComponent,
    PdgComponent,
    AjoutuserComponent,
    ModificationuserComponent,
    VendeursComponent,
    ArticlesComponent,
    CdmajoutComponent,
    LogoutComponent,
    ArticleAjoutComponent,
    ArticleModifComponent,
    VendeurAjoutComponent,
    VendeurModifComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS, useClass: BasicAuthHtppInterceptorService, multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

