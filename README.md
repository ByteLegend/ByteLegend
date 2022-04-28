# ByteLegend - learn programming while playing a game

[![check](https://github.com/ByteLegend/ByteLegend/actions/workflows/check.yml/badge.svg)](https://github.com/ByteLegend/ByteLegend/actions/workflows/check.yml)

[简体中文](./README.zh.md)

## What is ByteLegend

[ByteLegend](https://bytelegend.com) is a free, opensource MMORPG game where you acquire realworld high-paying programming skills.

![Index page](https://raw.githubusercontent.com/ByteLegend/ByteLegend/master/docs/images/index-page-en.png)

## How to play

- Go to [ByteLegend](https://bytelegend.com), click "Log in" at right-top corner, then log in with your GitHub account.
- Like any other games, you just play the game, by talking to NPCs, collecting items, finishing the missions etc.
- Unlike any other games, your get real, high-paid-job-ready programming skills in the real world!
  
## Why ByteLegend

- Free
  - Yes, it's free, as in "free beer". :-)
- Opensource
  - Yes, it's opensource. This means everything you see in the game is customizable: game map, story, NPC conversations, i18n texts, ...
  - Please don't hesitate to create issues/pull requests for anything bad you see in the game. We and thousands of players appreciate your contribution very much.
  - See [here](https://github.com/ByteLegend/ByteLegend/blob/master/docs/en/CONTRIBUTING.md) for more information on how to contribute.
- Fun
  - It's a game!
- Internationalization
  - We support **ALL** languages on this planet since day 1. You just need to click and select language on right-top corner of [ByteLegend](https://bytelegend.com).
  - As we said above, you can make a contribution to anything you see in [ByteLegend](https://bytelegend.com), including fixing bad translations or adding more languages to the game.
  - Check out [here](https://github.com/ByteLegend/ByteLegend/blob/master/docs/en/i18n.md) to see how to add a language or help us improve translation quality.

## Contact

Join us at [Discord](https://discord.gg/35RreUUGWt) or contact us via [contact@bytelegend.com](mailto:contact@bytelegend.com).

## Credits

This game would be impossible without the community and the opensource world, so we make it free and opensource to benefit more people.

To see the software and game materials used in this game, go to the right-bottom corner of [ByteLegend](https://bytelegend.com) and click `Credits` button.

## Contributing

We appreciate your contribution to anything in the game:

- Any bad texts: typo, wording or translation.
- Game map errors: bad tile image, incorrect marking, etc..
- Bugs: frontend/backend/game script.

Please read the [contributing guide](https://github.com/ByteLegend/ByteLegend/blob/master/docs/en/CONTRIBUTING.md) before you start.

## Start locally

Please make sure your `JAVA_HOME` configured to JDK 11.

- `git clone https://github.com/ByteLegend/ByteLegend`
- `cd ByteLegend`
- `git submodule update --init -- game-data`
- Run `./gradlew server-opensource:bootRun`, then access `http://localhost:8080`.

The first run may take minutes because all game resources need to be generated. Subsequent runs should start in seconds.

Please note that `server` git submodule hasn't been opensource yet, as we have complex backend services/credentials there (GitHub webhook, GitHub app, k8s secrets, etc.).
However, we do provide a opensource version of backend service in `server-opensource` module.

See [here](https://github.com/ByteLegend/ByteLegend/blob/master/docs/en/game-code-contributor-guide.md) for more details.
