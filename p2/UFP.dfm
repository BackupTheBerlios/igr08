object GLForm2D: TGLForm2D
  Left = 607
  Top = 334
  Width = 408
  Height = 434
  Caption = 'Lineas y Curvas'
  Color = clBtnFace
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
  object SaveDialog1: TSaveDialog
    Left = 24
  end
  object OpenDialog1: TOpenDialog
    Left = 48
  end
  object MainMenu1: TMainMenu
    object Archivo1: TMenuItem
      Caption = '&Archivo'
      object Nuevo1: TMenuItem
        Caption = 'Nuevo...'
        OnClick = Nuevo1Click
      end
      object Abrir1: TMenuItem
        Caption = 'Abrir...'
        OnClick = Abrir1Click
      end
      object Guardar1: TMenuItem
        Caption = 'Guardar...'
        OnClick = Guardar1Click
      end
      object Salir1: TMenuItem
        Caption = 'Salir...'
        OnClick = Salir1Click
      end
    end
    object Dibujar1: TMenuItem
      Caption = 'Dibujar'
      object Lineas1: TMenuItem
        Caption = 'Lineas'
        OnClick = Lineas1Click
      end
    end
  end
end
