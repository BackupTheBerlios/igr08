object GLForm2D: TGLForm2D
  Left = 317
  Top = 155
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
  OnMouseMove = FormMouseMove
  OnMouseUp = FormMouseUp
  OnMouseWheelDown = FormMouseWheelDown
  OnMouseWheelUp = FormMouseWheelUp
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
      end
      object Salir1: TMenuItem
        Caption = 'Salir...'
        OnClick = Salir1Click
      end
    end
    object Dibujar1: TMenuItem
      Caption = 'Dibujar'
      object Lineas1: TMenuItem
        Caption = 'Lineas...'
        OnClick = Lineas1Click
      end
      object Arcos1: TMenuItem
        Caption = 'Arcos...'
        OnClick = Arcos1Click
      end
      object Espirales1: TMenuItem
        Caption = 'Espiral...'
        OnClick = Espirales1Click
      end
      object Bezier1: TMenuItem
        Caption = 'Bezier...'
        OnClick = Bezier1Click
      end
      object CurvaBSplines1: TMenuItem
        Caption = 'B-Splines...'
        OnClick = CurvaBSplines1Click
      end
    end
    object Escena1: TMenuItem
      Caption = 'Escena'
      object Zoom1: TMenuItem
        Caption = 'Zoom...'
        OnClick = Zoom1Click
      end
      object ZoomProgresivo1: TMenuItem
        Caption = 'Zoom Progresivo...'
        OnClick = ZoomProgresivo1Click
      end
    end
    object Editar1: TMenuItem
      Caption = 'Editar'
      object Seleccionar1: TMenuItem
        Caption = 'Seleccionar...'
        OnClick = Seleccionar1Click
      end
      object Borrar1: TMenuItem
        Caption = 'Borrar...'
        OnClick = Borrar1Click
      end
      object Recorte1: TMenuItem
        Caption = 'Recorte'
        OnClick = Recorte1Click
      end
    end
    object Inspeccionar1: TMenuItem
      Caption = 'Inspeccionar Escena'
      OnClick = Inspeccionar1Click
    end
  end
end
