object GLForm2D: TGLForm2D
  Left = 399
  Top = 162
  Width = 608
  Height = 630
  Caption = 'Arkanoid'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  Menu = MainMenu1
  OldCreateOrder = False
  OnKeyDown = FormKeyDown
  OnPaint = FormPaint
  PixelsPerInch = 96
  TextHeight = 13
  object Timer1: TTimer
    Left = 24
  end
  object MainMenu1: TMainMenu
    object Nuevo1: TMenuItem
      Caption = '&Archivo'
      object Nueva1: TMenuItem
        Caption = '&Nueva'
        OnClick = Nueva1Click
      end
      object Iniciar1: TMenuItem
        Caption = '&Iniciar'
      end
      object Salir1: TMenuItem
        Caption = '&Salir'
      end
    end
    object Detalles1: TMenuItem
      Caption = '&Obstáculos'
      object Convexo1: TMenuItem
        Caption = '&Convexos'
      end
      object Circulos1: TMenuItem
        Caption = '&Circulos'
      end
    end
  end
end
