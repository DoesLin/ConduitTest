import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { PdgComponent } from './pdg/pdg.component';
import { AjoutuserComponent } from './ajoutuser/ajoutuser.component';
import { ModificationuserComponent } from './modificationuser/modificationuser.component';
import { CdmComponent } from './cdm/cdm.component';
import { VendeurComponent } from './vendeur/vendeur.component';
import { ArticleComponent } from './article/article.component';
import { CdmajoutComponent } from './cdmajout/cdmajout.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LogoutComponent } from './logout/logout.component';
import { BasicAuthHtppInterceptorService } from './service/basic-auth-htpp-interceptor.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';



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
    LogoutComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS, useClass: BasicAuthHtppInterceptorService, multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

