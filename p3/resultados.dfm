object Form1: TForm1
  Left = 199
  Top = 149
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
    Left = 64
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
    Width = 6
    Height = 13
    Caption = '3'
  end
end
