import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { LanguagesService } from 'src/app/services/languages/languages.service';
import { Language } from 'src/app/models/Language';



@Component({
    selector: 'app-create-language-comp',
    templateUrl: './create-language.component.html',
    styleUrls: ['./create-language.component.css'],

  })

export class CreateLanguageComponent implements OnInit {
  model: any = {};
  submitted: boolean;
  @Output() save: EventEmitter<any> = new EventEmitter();
  @Output() cancel: EventEmitter<any> = new EventEmitter();

  constructor(
    private languageService: LanguagesService) {}

  public ngOnInit() {
        this.model = {};
        this.submitted = false;
  }

  onSubmit(): void {
    // validate
    if (!this.validate()) {
        console.log('onSubmit: validation error');
        return;
    }
    // call service method(
    this.submitted = true;

    const newLanguage: Language = new Language();
    newLanguage.langName = this.model.langName;
    newLanguage.langCode = this.model.langCode;

    console.log(newLanguage);
    console.log(JSON.stringify(newLanguage));

    this.languageService.create(newLanguage)
    .subscribe((language: Language) => { // succesfully created a new user so redirect to the manage user page
        this.save.emit(`Create new language for ${newLanguage.langName}`);
    });


}


onCancel() {
    //
    this.cancel.emit('canceled');
}

validate(): boolean {
    console.log('this.model.langName', this.model.langName,
        'this.isEmpty (this.model.langName)', this.isEmpty (this.model.langName));
    console.log('this.model.langCode', this.model.langCode,
        'this.isEmpty (this.model.langCode)', this.isEmpty (this.model.langCode));

    return (
        !this.isEmpty (this.model.langName)
        && !this.isEmpty(this.model.langCode)
    );
}
isEmpty(str: string): boolean {
    if (str && typeof(str) === 'string') {
        if (str.length > 0) {
            return false;
        }
    }

    return true;
}

get diagnostics() {
    return 'model = ' + JSON.stringify(this.model)
      + ', submitted = ' + this.submitted;
  }
}
