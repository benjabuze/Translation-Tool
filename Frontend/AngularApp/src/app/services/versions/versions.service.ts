import { Injectable } from '@angular/core';
import {map, catchError} from 'rxjs/operators';
import { Observable, of } from 'rxjs';
import { Http, Headers, RequestOptions } from '@angular/Http';
import { UserLoginService } from '../user.login/user.login.service';
import {Version} from '../../models/Version';


@Injectable({providedIn: 'root'})
export class VersionService {

    constructor(private http: Http, private userLoginService: UserLoginService) {}

    getAll() {
        const headers = new Headers({
            'Content-Type': 'application/Json'
        });
        const options = new RequestOptions({ headers: headers});

        return this.http.get('/api/versions', options)
        .pipe(
            map((resp: any) => {
                console.log('versionService', resp);
                if (resp) {
                    const respBody = resp.json();
                    console.log(' file-functions.component language list: ', respBody);
                    console.log('respBody  ', respBody);
                   if (respBody.versionDetails) {
                        return <Array<Version>> respBody.versionDetails;
                    }

                }
                return [];
            })
        )
        .pipe(catchError(err => this.handleError(err)));
    }

    getByLangCode(langCode: String): Observable<Version[]> {


        // Add in JSON header and access token header
        const headers = new Headers({
            'Content-Type': 'application/Json'

        });
        const options = new RequestOptions({ headers: headers});

        return this.http.get(`/api/versions/${langCode}`, options)
        .pipe(
            map((resp: any) => {
                console.log('ManageLanguageComponent ajax response: ', resp);
                if (resp) {
                    const respBody = resp.json();
                    console.log('ManageLanguageComponent loaded languages list: ', respBody);
                    if (respBody) {
                        return respBody;
                    }
                }
                return <Array<Version>> [];
            })
        )
        .pipe(catchError(err => this.handleError(err)));
    }

    create(VersionDetails: Version): Observable<Version> {
        if (!this.userLoginService.isLoggedIn) {
            console.log('UserService.createUser: user not logged in');
            return null;
        }
        // Add in JSON header and access token header
        const headers = new Headers({
            'Content-Type': 'application/Json',
            'access-token': this.userLoginService.accessToken
        });

        const options = new RequestOptions({ headers: headers});

        return this.http.post('/api/versions', {
            versionDetails: {
                verNum: VersionDetails.verNum
            }
        }, options)
        .pipe(
            map((resp: any) => {
                if (resp) {
                    const respBody = resp.json();
                    console.log('VersionService.create: respBody', respBody);
                    const newVersion = respBody.versionDetails;
                    return newVersion;
                }
                return null;
            })
        )
        .pipe(catchError(err => this.handleError(err)));
    }

    private handleError(err: any): Observable<Version> {
        console.log('create version  failed: err =', err);
        return of(null);
    }

    deleteByVerNum(verNum: string): Observable<boolean> {
        // Add in JSON header and access token header
        const headers = new Headers({
            'Content-Type': 'application/Json'
        });
        const options = new RequestOptions({ headers: headers});
        const deleteVerUri = `/api/versions/${verNum}`;
        console.log(`deleteVerUri=${deleteVerUri}`);
        return this.http.delete(deleteVerUri, options)
            .pipe(
                map((results: any) => true),
                catchError(error => of(false))
            );
    }

    deleteLanguageByVer(langCode: string, verNum: string): Observable<boolean> {
       // Add in JSON header and access token header
       const headers = new Headers({
        'Content-Type': 'application/Json'
    });
    const options = new RequestOptions({headers: headers});
    const deleteLangUri = `/api/versions/${langCode}/${verNum}/`;
    console.log(`deleteVerUri=${deleteLangUri}`);
    return this.http.delete(deleteLangUri, options)
        .pipe(
            map((results: any) => true),
            catchError(error => of(false))
        );

    }
}

