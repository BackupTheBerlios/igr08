object GLForm2D: TGLForm2D
  Left = 511
  Top = 45
  BorderIcons = [biSystemMenu, biMinimize]
  BorderStyle = bsSingle
  Caption = 'Arkanoid'
  ClientHeight = 602
  ClientWidth = 500
  Color = clBtnFace
  DragMode = dmAutomatic
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  Menu = MainMenu1
  OldCreateOrder = False
  OnCreate = FormCreate
  OnDestroy = FormDestroy
  OnKeyDown = FormKeyDown
  OnMouseDown = FormMouseDown
  OnPaint = FormPaint
  OnResize = FormResize
  PixelsPerInch = 96
  TextHeight = 13
  object Timer1: TTimer
    Interval = 10
    OnTimer = Timer1Timer
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
        OnClick = Iniciar1Click
      end
      object Salir1: TMenuItem
        Caption = '&Salir'
      end
    end
    object Detalles1: TMenuItem
      Caption = '&Obst�culos'
      object Convexo1: TMenuItem
        Caption = '&Convexos'
        OnClick = Convexo1Click
      end
      object Circulos1: TMenuItem
        Caption = '&Circulos'
      end
    end
  end
end
