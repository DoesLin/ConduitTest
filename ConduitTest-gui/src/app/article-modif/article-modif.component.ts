import { Component, OnInit, AfterViewInit, ViewChild, Input } from '@angular/core';
import { Article, HttpClientService } from '../service/httpclient.service';
import { Router } from '@angular/router';
import { ArticlesComponent } from '../articles/articles.component';
import { ArticleDataService } from '../service/article-data.service';

@Component({
  selector: 'app-article-modif',
  templateUrl: './article-modif.component.html',
  styleUrls: ['./article-modif.component.scss']
})
export class ArticleModifComponent implements OnInit {

  article: Article = new Article('', '', '', '', 0, 0, null);

  message: string;

  constructor(private router: Router,
    private httpClientService: HttpClientService,
    private articledata: ArticleDataService) { }

  ngOnInit() {
    this.articledata.currentArticle.subscribe(article => this.article = article)
    console.log(this.article);
  }

  modifyArticle(): void {
    this.httpClientService.modifyArticle(this.article)
      .subscribe(
        data => {
          alert("Article modified successfully.");
          this.router.navigate([''])
        },
        error => {
          var msg = error['error']['message']
          alert('Modification fail: ' + msg.split(";")[0])
        });

  }

  annuler(): void {
    this.router.navigate([''])
  }

}
