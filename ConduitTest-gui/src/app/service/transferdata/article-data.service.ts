import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Article } from '../httpclient.service';

@Injectable({
  providedIn: 'root'
})
export class ArticleDataService {

  private articleSource = new BehaviorSubject(new Article('', '', '', '', 0, 0, null));
  currentArticle = this.articleSource.asObservable();

  constructor() { }

  changeArticle(article: Article) {
    this.articleSource.next(article)
  }
}
