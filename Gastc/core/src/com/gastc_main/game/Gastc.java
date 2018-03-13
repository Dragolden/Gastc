package com.gastc_main.game;

// Fenetres:
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// Classes:
import __Map__.*;
import Utilitaire.*;

public class Gastc extends ApplicationAdapter {
	// ### FENETRE ###
	
	protected static SpriteBatch CANVAS;
	
	// Textures autres:
	protected static Texture BORDURE1;
	protected static Texture BORDURE2;
	protected static Texture LINE;
	protected static Texture FOG_OF_WAR;
	protected static Texture CONTROL_BOARD;
	protected static Texture BUTTON_START;
	protected static Texture BUTTON_QUIT;
	
	// ### JEU ###
	
	protected static __Map__ _ActualMap_;
	protected static byte _ActualVision_;
	// 0 : wait
	// 1 : menu
	// 2 : in a map
	protected static boolean _Pause_;
	protected static int _Chronometre_;
	protected int _PositionSourisX_;
	protected int _PositionSourisY_;
	// camera:
	protected static int _DecalageX_;
	protected static int _DecalageY_;
	
	// ### METHODES DE FENETRE ###
	
	public void create () {
		CANVAS = new SpriteBatch();
		
		BORDURE1 = new Texture("pictures/others/border1.png");
		BORDURE2 = new Texture("pictures/others/border2.png");
		LINE = new Texture("pictures/others/line.png");
		FOG_OF_WAR = new Texture("pictures/others/fogOfWar.png");
		CONTROL_BOARD = new Texture("pictures/others/controlBoard.png");
		BUTTON_START = new Texture("pictures/others/button_start.png");
		BUTTON_QUIT = new Texture("pictures/others/button_quit.png");
		
		_ActualVision_ = 1;
		_Pause_ = false;
		_Chronometre_ = 0;
		_PositionSourisX_ = 0;
		_PositionSourisY_ = 0;
		_DecalageX_ = 0;
		_DecalageY_ = 0;
	}

	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		CANVAS.begin();
		
		canvasEffect();
		CANVAS.draw(BORDURE1, 0, 0);
		
		CANVAS.end();
	}
	
	public void dispose () {
		CANVAS.dispose();
		BORDURE1.dispose();
		BORDURE2.dispose();
		LINE.dispose();
		CONTROL_BOARD.dispose();
		BUTTON_START.dispose();
		BUTTON_QUIT.dispose();
	}
	
	// ### METHODES DU JEU ###
	
	// Interactions

	public void canvasEffect()
	{
		if (_ActualVision_ == 1) // menu
		{
			menuEffect();
		}
		else if (_ActualVision_ == 2) // sur une carte
		{
			mapEffect();
		}
	}
	
	//#########################################################
	//##################    MENU EFFECT    ####################
	//#########################################################
	
	public void menuEffect()
	{
		CANVAS.draw(BUTTON_START,  100,  500);
		CANVAS.draw(BUTTON_QUIT,  100,  400);
		
		// touches:
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT))
		{
			_PositionSourisX_ = Gdx.input.getX();
			_PositionSourisY_ = Gdx.input.getY();
			if (_ActualVision_ == 1) // menu
			{
				if (isMouseBetween(100, 228, 500, 564))
				{
					hitButton_Start();
				}
				else if (isMouseBetween(100, 228, 400, 464))
				{
					Gdx.app.exit(); // On ferme le jeu
				}
			}
		}
	}
	
	//#########################################################
	//##################    MAP EFFECT    #####################
	//#########################################################
	
	public void mapEffect()
	{
		// objets:
		_ActualMap_.displayTexture();
		
		// cases:
		for (int i=0 ; i<_ActualMap_.sizeY(); i++)
		{
			for (int l=0 ; l<_ActualMap_.sizeX(); l++)
			{
				CANVAS.draw(LINE, l*40+20+_DecalageX_, 680-i*40-60+_DecalageY_);
			}
		}
		
		CANVAS.draw(BORDURE2,  700,  0);
		CANVAS.draw(CONTROL_BOARD, 720, 20);
		
		_ActualMap_.activateFog();
		
		if (!_Pause_)
		{	
			// Gestion de la vitesse:
			_Chronometre_ = (_Chronometre_ +1) % 10000; // Revient a zero si il depasse 10000
			
			_ActualMap_.useOffensiveMove(); // Fait bouger/attaquer les unites sur la carte
		}
		
		// touches:
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
		{
			if (_DecalageX_ < 0)
			{
				_DecalageX_ += 3;
			}
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
		{
			if (_DecalageX_ > -_ActualMap_.sizeX()*40+680)
			{
				_DecalageX_ -= 3;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
		{
			if (_DecalageY_ < _ActualMap_.sizeY()*40-641)
			{
				_DecalageY_ += 3;
			}
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.UP))
		{
			if (_DecalageY_ > 0)
			{
				_DecalageY_ -= 3;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.P))
		{
			if (_Pause_)
			{
				_Pause_ = false;
			}
			else
			{
				_Pause_ = true;
			}
		}
	}
	
	// ### AUTRES ###
	
	public void hitButton_Start()
	{
		_ActualVision_ = 0; // wait
		
		_Pause_ = false;
		_Chronometre_ = 0;
		_DecalageX_ = 0;
		_DecalageY_ = 0;
		
		__List_Object__.initialiser(); // On met les objets dans leur liste	
		_ActualMap_ = new __Map__("test"); // On initialise la carte de test

		_ActualVision_ = 2; // in a map
	}
	
	public static int getChronometre()
	{
		return _Chronometre_;
	}
	
	public static void setActualVision(byte Vision)
	{
		_ActualVision_ = Vision;
	}
	
	// ### METHODES UTILITAIRES ###
	
	public boolean isMouseBetween(int LowX, int HighX, int LowY, int HighY)
	{
		return (_PositionSourisX_ > LowX && _PositionSourisX_ < HighX && _PositionSourisY_ > 680-HighY && _PositionSourisY_ < 680-LowY);
	}
	
	public static void display(String Picture, int PositionX, int PositionY)
	{
		Texture TEXTURE = new Texture(Picture);
		CANVAS.draw(TEXTURE, PositionX+_DecalageX_, PositionY+_DecalageY_);
	}
	
	public static void display(Texture TEXTURE, int PositionX, int PositionY)
	{
		CANVAS.draw(TEXTURE, PositionX+_DecalageX_, PositionY+_DecalageY_);
	}
	
	public static void displayFog(int PositionX, int PositionY)
	{
		CANVAS.draw(FOG_OF_WAR, PositionX+_DecalageX_, PositionY+_DecalageY_);
	}
	
}
