import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';

export class Article {
  constructor(
    public serial: string,
    public name: string,
    public categorie: string,
    public description: string,
    public prix: number,
    public quantite: number,
    public vendeurName: string,
  ) { }
}

export class Vendeur {
  constructor(
    public username: string,
    public password: string,
    public managername: string,
  ) { }
}

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  ArticleUrl = 'http://localhost:8080/articles';
  VendeurUrl = 'http://localhost:8080/vendeurs';

  constructor(
    private httpClient: HttpClient
  ) {
  }

  getArticles() {
    return this.httpClient.get<Article[]>(this.ArticleUrl);
  }

  public createArticle(article) {
    return this.httpClient.post<Article>(this.ArticleUrl, article);
  }

  public modifyArticle(article) {
    return this.httpClient.put<Article>(this.ArticleUrl, article);
  }

  public deleteArticle(article) {
    return this.httpClient.delete<Article>(this.ArticleUrl + "/" + article.serial);
  }

  getVendeurs() {
    return this.httpClient.get<Vendeur[]>(this.VendeurUrl);
  }

  public createVendeur(vendeur) {
    return this.httpClient.post<Vendeur>(this.VendeurUrl, vendeur);
  }

  public modifyVendeur(vendeur) {
    return this.httpClient.put<Vendeur>(this.VendeurUrl, vendeur);
  }

  public deleteVendeur(vendeur) {
    return this.httpClient.delete<Vendeur>(this.VendeurUrl + "/" + vendeur.username);
  }

}