import { Component, OnInit } from '@angular/core';
import { HttpClientService, Article } from '../service/httpclient.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-article-ajout',
  templateUrl: './article-ajout.component.html',
  styleUrls: ['./article-ajout.component.scss']
})
export class ArticleAjoutComponent implements OnInit {

  article: Article = new Article('', '', '', '', 0, 0, null);

  constructor(private router: Router,
    private httpClientService: HttpClientService) { }

  ngOnInit() {
  }

  addArticle(): void {
    if (this.article.serial == '' || this.article.serial == null) {
      alert("Article serial is empty.");
    } else if (this.article.name == '' || this.article.name == null) {
      alert("Article name is empty.");
    } else if (this.article.categorie == '' || this.article.categorie == null) {
      alert("Article categorie is empty.");
    } else if (this.article.description == '' || this.article.description == null) {
      alert("Article description is empty.");
    } else {
      this.httpClientService.createArticle(this.article)
        .subscribe(
          data => {
            alert("Article created successfully.");
            this.router.navigate([''])
          },
          error => {
            var msg = error['error']['message']
            alert('Creation fail: ' + msg.split(";")[0])
          });
    }
  }

  annuler(): void {
    this.router.navigate([''])
  }

}
