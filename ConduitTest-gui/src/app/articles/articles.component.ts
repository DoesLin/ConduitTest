import { Component, OnInit, Input, AfterViewInit, ViewChild } from '@angular/core';
import { HttpClientService, Article } from '../service/httpclient.service';
import { Router } from '@angular/router';
import { ArticleModifComponent } from '../article-modif/article-modif.component';
import { ArticleDataService } from '../service/article-data.service';

declare var $: any

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.scss']
})
export class ArticlesComponent implements OnInit {

  articles: Article[];
  articleModif = new Article('', '', '', '', 0, 0, null);

  constructor(private router: Router,
    private httpClientService: HttpClientService,
    private articledata: ArticleDataService) { }

  ngOnInit() {
    this.jquery_code();
    this.httpClientService.getArticles().subscribe(
      response => this.handleSuccessfulResponse(response),
    );
    this.articledata.currentArticle.subscribe(articleModif => this.articleModif = articleModif);
  }

  jquery_code() {
    $(function () {
      $("tbody tr").click(function () {
        $(this).addClass("s1").siblings().removeClass("s1");
        $(this).find(":radio").attr("checked", true);
      });
    });
  }

  handleSuccessfulResponse(response) {
    this.articles = response;
  }

  addArticle(): void {
    this.router.navigate(['article-ajout']);
  };

  modifyArticle(article: Article): void {
    this.articledata.changeArticle(article)
    this.router.navigate(['article-modif']);
  };

  deleteArticle(article: Article): void {
    this.httpClientService.deleteArticle(article)
      .subscribe(data => {
        this.articles = this.articles.filter(u => u !== article);
      })
  };
}