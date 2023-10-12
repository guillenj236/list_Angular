import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable, inject } from "@angular/core";
import { Livro } from "../models/livro";
import { Observable } from "rxjs";
@Injectable({
    providedIn: 'root'
  })
export class LivroService {
    API: string = 'http://localhost:8080/api/livro';
    http = inject(HttpClient);

  constructor() { }


  listAll(): Observable<Livro[]> {
    return this.http.get<Livro[]>(this.API);
  }

  save(livro: Livro): Observable<Livro> {
    return this.http.post<Livro>(this.API, livro);
  }

  update(livro: Livro): Observable<Livro> {
    return this.http.put<Livro>(this.API, livro);
  }
  delete(id: number): Observable<any> {

    let params = new HttpParams()
      .set('id', id.toString())
    return this.http.delete<any>(this.API, { params: params });
  }


  exemploErro(): Observable<Livro[]> {
    return this.http.get<Livro[]>(this.API + '/erro');
  }
}
