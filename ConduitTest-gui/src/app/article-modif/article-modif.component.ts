import { Component, OnInit } from '@angular/core';
import { Article, HttpClientService } from '../service/httpclient.service';
import { Router } from '@angular/router';
import { ArticleDataService } from '../service/transferdata/article-data.service';

@Component({
  selector: 'app-article-modif',
  templateUrl: './article-modif.component.html',
  styleUrls: ['./article-modif.component.scss']
})
export class ArticleModifComponent implements OnInit {

  article: Article = new Article('', '', '', '', 0, 0, null);

  constructor(private router: Router,
    private httpClientService: HttpClientService,
    private articledata: ArticleDataService) { }

  ngOnInit() {
    this.articledata.currentArticle.subscribe(article => this.article = article)
    console.log(this.article);
  }

  modifyArticle(): void {
    if (this.article.serial == '' || this.article.serial == null) {
      alert("Article serial is empty.");
    } else if (this.article.name == '' || this.article.name == null) {
      alert("Article name is empty.");
    } else if (this.article.categorie == '' || this.article.categorie == null) {
      alert("Article categorie is empty.");
    } else if (this.article.description == '' || this.article.description == null) {
      alert("Article description is empty.");
    } else {
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
  }

  annuler(): void {
    this.router.navigate([''])
  }

}
