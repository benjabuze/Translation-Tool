import { Component, OnInit } from '@angular/core';
import { StatisticsService }  from '../../../services/statistics/statistics.service';
import { VersionService } from '../../../services/versions/versions.service';
import { LanguagesService } from '../../../services/languages/languages.service';
@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent implements OnInit {
  firstNewKeys: string;
  firstApprovedKeys: string;
  firstTotalKeys: string;
  secondNewKeys: string;
  secondApprovedKeys: string;
  secondTotalKeys: string;
  firstVersion: string;
  secondVersion: string;

  model: any = {};
  currVersion = '';
  currLanguage = '';
  languages: any = {};

  constructor(
    private statisticsService: StatisticsService,
    private versionService: VersionService,
    private languageService: LanguagesService,
  ) { }

  async ngOnInit() {
    await this.getVersions();
    await this.getLanguages();
  }
  async getVersions() {
    const versions = await this.versionService.getAll().toPromise();
    this.model.versions = versions;
    this.currVersion = this.model.versions[0].verNum;
    console.log(this.currVersion);
  }

  async getLanguages() {
    const languages = await this.languageService.getAll().toPromise();
    this.languages.lang = languages;
    console.log(languages);
    this.currLanguage = this.languages.lang[0].langCode;
    console.log(this.currLanguage);
  }

  viewStatistics(language: string, versionNumber: string) {
    this.firstVersion = versionNumber;
    //Statistics for first version
    this.statisticsService.getNewKeys(language, versionNumber).subscribe(
      (keys) => {
        this.firstNewKeys = JSON.stringify(keys);
      }
    )
    this.statisticsService.getApprovedKeys(language, versionNumber).subscribe(
      (keys) => {
        this.firstApprovedKeys = JSON.stringify(keys);
      }
    )
    this.statisticsService.getTotalKeys(language, versionNumber).subscribe(
      (keys) => {
        this.firstTotalKeys = JSON.stringify(keys);
      }
    )
  }

  compareVersions(language: string, versionNumber: string) {
    this.secondVersion = versionNumber;
    //Statistics for second version
    this.statisticsService.getNewKeys(language, versionNumber).subscribe(
      (keys) => {
        this.secondNewKeys = JSON.stringify(keys);
      }
    )
    this.statisticsService.getApprovedKeys(language, versionNumber).subscribe(
      (keys) => {
        this.secondApprovedKeys = JSON.stringify(keys);
      }
    )
    this.statisticsService.getTotalKeys(language, versionNumber).subscribe(
      (keys) => {
        this.secondTotalKeys = JSON.stringify(keys);
      }
    )
  }
}