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
  object Edit1: TEdit
    Left = 256
    Top = 24
    Width = 121
    Height = 21
    TabOrder = 0
    Text = 'Edit1'
  end
  object Edit2: TEdit
    Left = 256
    Top = 56
    Width = 121
    Height = 21
    TabOrder = 1
    Text = 'Edit1'
  end
  object Edit3: TEdit
    Left = 256
    Top = 88
    Width = 121
    Height = 21
    TabOrder = 2
    Text = 'Edit1'
  end
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
