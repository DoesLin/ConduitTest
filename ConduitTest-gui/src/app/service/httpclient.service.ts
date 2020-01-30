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

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  ArticleUrl = 'http://localhost:8080/articles';

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

}