import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { LoginComponent } from './login/login.component';
import { VendeursComponent } from './vendeurs/vendeurs.component';
import { ArticlesComponent } from './articles/articles.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LogoutComponent } from './logout/logout.component';
import { BasicAuthHtppInterceptorService } from './service/authentication/basic-auth-htpp-interceptor.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ArticleAjoutComponent } from './article-ajout/article-ajout.component';
import { ArticleModifComponent } from './article-modif/article-modif.component';
import { VendeurAjoutComponent } from './vendeur-ajout/vendeur-ajout.component';
import { VendeurModifComponent } from './vendeur-modif/vendeur-modif.component';
import { ChefmagasinsComponent } from './chefmagasins/chefmagasins.component';
import { ChefmagasinAjoutComponent } from './chefmagasin-ajout/chefmagasin-ajout.component';
import { ChefmagasinModifComponent } from './chefmagasin-modif/chefmagasin-modif.component';



@NgModule({

  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    LoginComponent,
    VendeursComponent,
    ArticlesComponent,
    LogoutComponent,
    ArticleAjoutComponent,
    ArticleModifComponent,
    VendeurAjoutComponent,
    VendeurModifComponent,
    ChefmagasinsComponent,
    ChefmagasinAjoutComponent,
    ChefmagasinModifComponent,
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

