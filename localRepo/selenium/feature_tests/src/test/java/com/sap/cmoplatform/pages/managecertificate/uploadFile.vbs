Set WshShell = WScript.CreateObject("WScript.Shell")
Set args = WScript.Arguments
directory = args.Item(0)
WshShell.SendKeys directory
WshShell.SendKeys "{ENTER}"
