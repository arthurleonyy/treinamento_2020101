import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OperacoesComponent } from './operacoes.component';

describe('OperacoesComponent', () => {
  let component: OperacoesComponent;
  let fixture: ComponentFixture<OperacoesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OperacoesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OperacoesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
