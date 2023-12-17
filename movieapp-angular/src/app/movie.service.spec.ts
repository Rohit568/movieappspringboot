import { TestBed } from '@angular/core/testing';

import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { MovieService } from './movie.service';
import { NgModule } from '@angular/core';

describe('MovieService', () => {
  
  let service: MovieService ;
  let httpMock : HttpTestingController;
  let apiUrl = "http://localhost:8321/";
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers:[MovieService]
    }).compileComponents();
  });
  beforeEach(() => {
    
    service = TestBed.inject(MovieService);
    httpMock = TestBed.inject(HttpTestingController);
    
    
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('login() method should tested', ()=>{

    const username = 'testuser';
    const password = 'testpassword';

    // Mock the response you expect from the server
    const token= { token: 'mockToken' };

    service.login(username, password)

    const req = httpMock.expectOne(apiUrl + "auth/login");
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual({ username, password });

    // Provide a mock response for the request
    req.flush(null);
  });
  
  })



