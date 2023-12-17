import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Movie2Component } from './movie2.component';

describe('Movie2Component', () => {
  let component: Movie2Component;
  let fixture: ComponentFixture<Movie2Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Movie2Component ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Movie2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
