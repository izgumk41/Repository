import random #generating random numbers
import sys # use sys.exit to exit program
import pygame
from pygame.locals import * #basic pygame imports

#global variables/settingss
FPS = 32
SCREENWIDTH = 289
SCREENHEIGHT = 511
SCREEN = pygame.display.set_mode((SCREENWIDTH,SCREENHEIGHT))
GROUNDY = SCREENHEIGHT * 0.8
GAME_SPRITES = {}
GAME_SOUNDS = {}
PLAYER = r'C:\Users\izan1\Desktop\Python flappy bird\gallery\sprites\bird.png'
BACKGROUND = r'C:\Users\izan1\Desktop\Python flappy bird\gallery\sprites\background.png'
PIPE = r'C:\Users\izan1\Desktop\Python flappy bird\gallery\sprites\pipe.png'

def welcomeScreen():
    """
    shows welcome image on screen
    """

    playerx = int(SCREENWIDTH/5)
    playery = int((SCREENHEIGHT-GAME_SPRITES['player'].get_height())/2)
    messagex = int((SCREENWIDTH-GAME_SPRITES['message'].get_height())/2)
    messagey = int(SCREENHEIGHT*0.13)
    basex = 0

    while True:

        for event in pygame.event.get():
            #if user clicks on cross button, close the game
            if event.type == QUIT or (event.type == KEYDOWN and event.key == K_ESCAPE):
                pygame.quit()
                sys.exit()

            #if user presses space or up key start game
            elif event.type == KEYDOWN and (event.key == K_SPACE or event.key == K_UP):
                return

            else:
                SCREEN.blit(GAME_SPRITES['background'],(0, 0))
                SCREEN.blit(GAME_SPRITES['player'],(playerx, playery))
                SCREEN.blit(GAME_SPRITES['message'],(messagex, messagey))
                SCREEN.blit(GAME_SPRITES['base'],(basex, GROUNDY))
                pygame.display.update()
                FPSCLOCK.tick(FPS)

def mainGame():
    score = 0
    playerx = int(SCREENWIDTH/5)
    playery = int(SCREENHEIGHT/2)
    basex = 0
    
    #create 2 pipes 
    newPipe1 = getRandomPipe()
    newPipe2 = getRandomPipe()

    #list of upper pipes
    upperPipes = [
        {'x': SCREENWIDTH+200, 'y':newPipe1[0]['y']},
        {'x': SCREENWIDTH+200+(SCREENWIDTH/2), 'y':newPipe2[0]['y']},
    ]

    #list of lower pips
    lowerPipes = [
        {'x': SCREENWIDTH+200, 'y':newPipe1[1]['y']},
        {'x': SCREENWIDTH+200+(SCREENWIDTH/2), 'y':newPipe2[1]['y']},
    ]


    pipeSpeedx = -4

    playerSpeedY = -9
    playerMaxSpeedY = 10
    playerMinSpeedY = -8
    playerAccY = 1


    playerFlapAccv = -8 #speed while flapping
    playerFlapped = False #true when bird is flapping

    while True:
        for event in pygame.event.get():
            if event.type == QUIT or (event.type == KEYDOWN and event.key == K_ESCAPE):
                pygame.quit()
                sys.exit()
            if event.type == KEYDOWN and (event.key == K_SPACE or event.key == K_UP):
                if playery > 0:
                    playerSpeedY = playerFlapAccv
                    playerFlapped = True

        crashTest = isCollide(playerx,playery, upperPipes, lowerPipes) #returns true if player crashed
        if crashTest:
            return
        
        #check for score
        playerMidPos = playerx + GAME_SPRITES['player'].get_width()/2
        for pipe in upperPipes:
            pipeMidPos = pipe['x'] + GAME_SPRITES['pipe'][0].get_width()/2
            if pipeMidPos <= playerMidPos < pipeMidPos + 4:
                score += 1
                print(f"your score is {score}")
        
        if playerSpeedY < playerMaxSpeedY and not playerFlapped:
            playerSpeedY += playerAccY
        
        if playerFlapped:
            playerFlapped = False
        
        playerHeight = GAME_SPRITES['player'].get_height()
        playery = playery + min(playerSpeedY, GROUNDY-playery-playerHeight)
                

        #move pipes to the left
        for upperPipe, lowerPipe in zip(upperPipes, lowerPipes):
            upperPipe['x'] += pipeSpeedx
            lowerPipe['x'] += pipeSpeedx
        
        #add new pipe when first pipe about to cross the leftmost of the screen
        if 0 < upperPipes[0]['x'] < 5:
            newpipe = getRandomPipe()
            upperPipes.append(newpipe[0])
            lowerPipes.append(newpipe[1])

        #if pipe out of screen remove
        if upperPipes[0]['x'] < -GAME_SPRITES['pipe'][0].get_width():
            upperPipes.pop(0)
            lowerPipes.pop(0)

        #add sprites to screen
        SCREEN.blit(GAME_SPRITES['background'],(0,0))
        SCREEN.blit(GAME_SPRITES['base'],(basex,GROUNDY))
        for upperPipe, lowerPipe in zip(upperPipes, lowerPipes):
            SCREEN.blit(GAME_SPRITES['pipe'][0],(upperPipe['x'],upperPipe['y']))
            SCREEN.blit(GAME_SPRITES['pipe'][1],(lowerPipe['x'],lowerPipe['y']))
        SCREEN.blit(GAME_SPRITES['player'],(playerx,playery))
        
        
        myDigits = [int(x) for x in list(str(score))]
        width = 0
        for digit in myDigits:
            width += GAME_SPRITES['numbers'][digit].get_width()
        
        Xoffset = (SCREENWIDTH - width) / 2

        for digit in myDigits:
            SCREEN.blit(GAME_SPRITES['numbers'][digit],(Xoffset,SCREENHEIGHT*0.12))
            Xoffset += GAME_SPRITES['numbers'][digit].get_width()


        pygame.display.update()
        FPSCLOCK.tick(FPS)

def isCollide(playerx,playery, upperPipes, lowerPipes):
    if playery> GROUNDY - 30 or playery < 0:
        return True
    
    for pipe in upperPipes:
        pipeHeight = GAME_SPRITES['pipe'][0].get_height()
        if(playery < pipeHeight + pipe['y'] and abs(playerx-pipe['x']) < GAME_SPRITES['pipe'][0].get_width()):
           return True

    for pipe in lowerPipes:
        if(playery + GAME_SPRITES['player'].get_height() > pipe['y']) and (abs(playerx-pipe['x']) < GAME_SPRITES['pipe'][0].get_width()):
           return True
    return False

def getRandomPipe():
    """
    generate positions of two pipes(one straight and one rotated) for blitting on the screen
    """

    pipeHeight = GAME_SPRITES['pipe'][0].get_height()
    offset = SCREENHEIGHT/3
    y2 = offset + random.randrange(0, int(SCREENHEIGHT - GAME_SPRITES['base'].get_height() - 1.2 * offset))
    pipeX = SCREENWIDTH + 10
    y1 = pipeHeight - y2 + offset
    pipe = [
        {'x':pipeX, 'y': -y1}, #upper pipe
        {'x':pipeX, 'y': y2}
    ]

    return pipe
if __name__ == "__main__":
    #initialization of game
    pygame.init() #Initialize all pygame's modules
    FPSCLOCK = pygame.time.Clock()
    pygame.display.set_caption('FlappyBird')
    GAME_SPRITES['numbers'] = (
        pygame.image.load(r'C:\Users\izan1\Desktop\Python flappy bird\gallery\sprites\0.png').convert_alpha(),
        pygame.image.load(r'C:\Users\izan1\Desktop\Python flappy bird\gallery\sprites\1.png').convert_alpha(),
        pygame.image.load(r'C:\Users\izan1\Desktop\Python flappy bird\gallery\sprites\2.png').convert_alpha(),
        pygame.image.load(r'C:\Users\izan1\Desktop\Python flappy bird\gallery\sprites\3.png').convert_alpha(),
        pygame.image.load(r'C:\Users\izan1\Desktop\Python flappy bird\gallery\sprites\4.png').convert_alpha(),
        pygame.image.load(r'C:\Users\izan1\Desktop\Python flappy bird\gallery\sprites\5.png').convert_alpha(),
        pygame.image.load(r'C:\Users\izan1\Desktop\Python flappy bird\gallery\sprites\6.png').convert_alpha(),
        pygame.image.load(r'C:\Users\izan1\Desktop\Python flappy bird\gallery\sprites\7.png').convert_alpha(),
        pygame.image.load(r'C:\Users\izan1\Desktop\Python flappy bird\gallery\sprites\8.png').convert_alpha(),
        pygame.image.load(r'C:\Users\izan1\Desktop\Python flappy bird\gallery\sprites\9.png').convert_alpha()
    )

    GAME_SPRITES['message'] = pygame.image.load(r'C:\Users\izan1\Desktop\Python flappy bird\gallery\sprites\message.png').convert_alpha()
    GAME_SPRITES['base'] = pygame.image.load(r'C:\Users\izan1\Desktop\Python flappy bird\gallery\sprites\base.png').convert_alpha()
    GAME_SPRITES['pipe'] = (
        pygame.transform.rotate(pygame.image.load(PIPE).convert_alpha(),180),
        pygame.image.load(PIPE).convert_alpha()
    )
    
    #game sounds


    GAME_SPRITES['background'] = pygame.image.load(BACKGROUND).convert()
    GAME_SPRITES['player'] = pygame.image.load(PLAYER).convert_alpha()  

    while True:
        welcomeScreen() # shows welcome screen until tap
        mainGame() # main game functionm
    

