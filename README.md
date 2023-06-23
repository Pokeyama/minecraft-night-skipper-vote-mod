# NightSkipperVoteMod (Server-Side)

## Overview

NightSkipperVoteMod is a Minecraft Forge mod designed for server-side usage. This mod allows players on the server to vote to skip the night cycle and progress to morning. Players can cast their votes in favor or against using commands.

## Features

- **Works on the server-side. It does not function if installed on the client-side.**
- When night falls on the server, the voting process is initiated, and the night can be skipped based on the outcome.
- Language support for English (en_us) and Japanese (ja_JP).
- Players can switch the language to English using the command "/lang en" or to Japanese using "/lang ja".

## Installation Guide
https://shockbyte.com/billing/knowledgebase/48/How-to-Install-Forge-Mods-on-Your-Minecraft-Server.html

## Usage

1. When night falls on the server, the voting process begins.
2. Players can use the "/y" command to vote in favor or the "/n" command to vote against.
3. If a player does not vote, their vote is considered as "undecided".
4. If the number of "in favor" votes or the combined number of "in favor" and "undecided" votes is greater than or equal to the number of "against" votes, the night is skipped, and it transitions to morning.
5. Players can switch the language to English using the "/lang en" command or to Japanese using the "/lang ja" command.

Note: The voting process is only executed when there are at least two players on the server.

## License

This mod is released under the MIT License.