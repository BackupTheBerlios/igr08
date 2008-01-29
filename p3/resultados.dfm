object Form1: TForm1
  Left = 212
  Top = 174
  BorderIcons = [biSystemMenu]
  BorderStyle = bsDialog
  Caption = 'Form1'
  ClientHeight = 205
  ClientWidth = 299
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  Visible = True
  OnCreate = FormCreate
  OnDestroy = FormDestroy
  PixelsPerInch = 96
  TextHeight = 13
  object LabelPuntos: TLabel
    Left = 16
    Top = 56
    Width = 84
    Height = 36
    Caption = 'Puntos'
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -32
    Font.Name = 'Times New Roman'
    Font.Style = []
    ParentFont = False
  end
  object LabelVidas: TLabel
    Left = 16
    Top = 24
    Width = 72
    Height = 13
    Caption = 'Vidas restantes'
  end
  object Puntos: TLabel
    Left = 40
    Top = 104
    Width = 34
    Height = 75
    Caption = '0'
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlue
    Font.Height = -67
    Font.Name = 'Times New Roman'
    Font.Style = [fsBold, fsItalic]
    ParentFont = False
  end
  object Vidas: TLabel
    Left = 112
    Top = 24
    Width = 9
    Height = 16
    Caption = '3'
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clRed
    Font.Height = -13
    Font.Name = 'MS Sans Serif'
    Font.Style = [fsBold]
    ParentFont = False
  end
  object Label1: TLabel
    Left = 136
    Top = 24
    Width = 153
    Height = 13
    Caption = 'PUNTACIONES RECORDS'
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = 'MS Sans Serif'
    Font.Style = [fsBold, fsUnderline]
    ParentFont = False
  end
  object Label2: TLabel
    Left = 168
    Top = 56
    Width = 39
    Height = 13
    Caption = 'Label2'
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clFuchsia
    Font.Height = -11
    Font.Name = 'MS Sans Serif'
    Font.Style = [fsBold]
    ParentFont = False
  end
end
