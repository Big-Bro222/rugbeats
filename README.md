### Brief

It is a maze game in which 2 players have to compete to get the chest and bring it home. They have to move according to the beats of music.

### Functionalities

- Image sequence based Animation in `AniManager` and `FrameAnim`
- Music play and beat match in `AudioManager`
- Game map creating in MazeGenerator
- Game scene generation according to game map created by user.

  `GameController` is responsible for all gameplay logic and beat animation

  `GameModel` is responsible for game scene management and wall type calculation

- Player control is implemented in `Player` which is responsible for keyboard events and some player animation.
- `Utils` include some utility functions like color interpolation and value clamp
- `GLOBAL` is responsible to store some CONSTANTS and to facilitate data communication between different classes

### Simplifications

- Player movement is not animated as it may take sometime for the Player to move, the time consumption may influence the player to operate next movement
- Character selection doesn't include hat/pants customization as we can not find suitable free image resources for this purpose
- Realtime beats detection is not implemented as it requires external library.

### How to use

This project was created using `Intellij IDEA`. But you can also open it in `Eclipse`

**a)Run the main function in class Main**

[](https://www.notion.so/d40af808fee84c5db54a0554ea64ae98#b442b2ebad344f349bdfbefbba25f3a7)

**b)Use keyboard to move players together to start game**

[](https://www.notion.so/d40af808fee84c5db54a0554ea64ae98#f725aa29f24243d1a5263ff6dd3d469f)

**c)Start Playing**
