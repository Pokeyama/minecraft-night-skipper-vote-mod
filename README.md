# NightSkipperVoteMod (Server-side)

NightSkipperVoteMod is a Minecraft Forge mod designed for server-side use. 
It allows players on a server to vote for skipping the night cycle and advancing to the morning. 
The mod provides a voting system where players can cast their votes by typing "/y" for agreement or "/n" for opposition. 
The mod takes into account the number of votes and includes a hold option for players who choose not to vote.

## Features

- Initiates voting when night falls on the server.
- Players can vote by typing "/y" for agreement or "/n" for opposition.
- Supports a hold option for players who choose not to vote.
- Skips the night cycle and advances to morning if there are more "agree" votes or an equal number of "agree" and "hold" votes.
- Includes language support for English (en_us) and Japanese (ja_JP).
- Players can switch the mod's language by typing "/lang en" for English or "/lang ja" for Japanese.

## Usage

1. When night falls on the server, the voting process starts.
2. Players can cast their votes by typing "/y" for agreement or "/n" for opposition.
3. Players can choose to hold their vote by not casting a vote.
4. If the number of "agree" votes or the combined number of "agree" and "hold" votes is greater than the number of "oppose" votes, the night cycle will be skipped, and the server will advance to morning.
5. Players can switch the mod's language by typing "/lang en" for English or "/lang ja" for Japanese.

Note: The mod requires at least 2 players on the server for the voting process to occur.

## License

This mod is released under the MIT License.