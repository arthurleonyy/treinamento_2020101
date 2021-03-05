import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { OperacoesComponent } from './operacoes.component';

describe('OperacoesComponent', () => {
  let component: OperacoesComponent;
  let fixture: ComponentFixture<OperacoesComponent>;

  beforeEach(waitForAsync(() => {
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
