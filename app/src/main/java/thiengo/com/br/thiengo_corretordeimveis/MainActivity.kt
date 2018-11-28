package thiengo.com.br.thiengo_corretordeimveis

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity :
    AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_main )
        setSupportActionBar( toolbar )

        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener( toggle )
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener( this )

        aboutPage()
    }

    override fun onBackPressed() {
        if( drawer_layout.isDrawerOpen( GravityCompat.START ) ){
            drawer_layout.closeDrawer( GravityCompat.START )
        }
        else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected( item: MenuItem ): Boolean {
        when (item.itemId) {
            R.id.nav_contact -> {
                whatsAppHelp()
            }
            else -> {
                aboutPage()
            }
        }

        drawer_layout.closeDrawer( GravityCompat.START )

        /*
         * Para não mudar o item selecionado em menu gaveta. Status
         * útil para nosso exemplo de única tela.
         * */
        return false
    }

    /*
     * Método ouvidor para permitir que o usuário entre em contato
     * com o WhatsApp correto com apenas um acionamento em tela.
     * */
    fun whatsAppHelp(){
        /* O número abaixo é fictício. */
        val whatsAppUri = Uri.parse( "smsto:${AboutFragment.NUMBER}" )
        val intent = Intent( Intent.ACTION_SENDTO, whatsAppUri )

        intent.setPackage( "com.whatsapp" )

        /*
         * Garantindo que a Intent somente será acionada se o
         * aplicativo WhatsApp estiver presente no aparelho.
         * */
        if( intent.resolveActivity( packageManager ) != null ){
            startActivity( intent )
        }
        else{
            Toast
                .makeText(
                    this,
                    getString(R.string.whatsapp_needed_info),
                    Toast.LENGTH_SHORT
                )
                .show()
        }
    }

    /*
     * Método responsável por invocar a AboutFragment como
     * o fragmento atual em tela.
     * */
    private fun aboutPage(){
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = AboutFragment()

        transaction.replace( R.id.fl_frag_container, fragment )
        transaction.commit()
    }

    /*
     * Método responsável por conter o código de atualização
     * de título da atividade. Seria invocado em todos os
     * fragmentos relacionados aos itens de menu gaveta.
     * */
    fun updateActivityTitle( title: String ){
        toolbar.title = title
    }
}