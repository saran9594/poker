package william.miranda.poker.activities;

import william.miranda.poker.controller.PokerInputProcessor;
import william.miranda.poker.model.PokerGame;
import william.miranda.poker.view.ViewUtils;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PokerGameGDX extends Game
{
	OrthographicCamera camera;
	SpriteBatch batch;
		
	PokerGame pokerGame;
	
	@Override
	public void create()
	{
		Texture.setEnforcePotImages(false);
		
		PokerInputProcessor inputProcessor = new PokerInputProcessor();
		Gdx.input.setInputProcessor(inputProcessor);
		
		ViewUtils.setLargura(Gdx.graphics.getWidth());
		ViewUtils.setAltura(Gdx.graphics.getHeight());
		
		//create the camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, ViewUtils.getLargura(), ViewUtils.getAltura());
		
		batch = new SpriteBatch();
		
		//inicia as classes do jogo
		pokerGame = PokerGame.getInstance();
		pokerGame.iniciarNovaRodada();
		pokerGame.rodarJogo();
	}

	@Override
	public void dispose()
	{
		batch.dispose();
	}

	@Override
	public void pause()
	{
		
	}

	@Override
	public void render()
	{
		super.render();
		
		Gdx.gl.glClearColor(0, 0.25f, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		//tell the camera to update its matrices.
		camera.update();
		
		//tell the SpriteBatch to render in the
		//coordinate system specified by the camera.
		batch.setProjectionMatrix(camera.combined);
		
		//begin a new batch and draw the bucket and all drops
		batch.begin();
		
		pokerGame.desenhar(batch);
		
		batch.end();
	}

	@Override
	public void resize(int arg0, int arg1)
	{
		
	}

	@Override
	public void resume()
	{
		
	}

}
