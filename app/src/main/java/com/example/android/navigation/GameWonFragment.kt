package com.example.android.navigation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.FragmentGameWonBinding


class GameWonFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_game_won, container, false)
        binding.nextMatchButton.setOnClickListener {
            view!!.findNavController().navigate(GameWonFragmentDirections.actionGameWonFragmentToGameFragment())
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.winner_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
        if(getShareIntent().resolveActivity(activity!!.packageManager)==null){
            menu.findItem(R.id.share).isVisible = false
        }
    }
    private fun getShareIntent(): Intent{
        var args=GameWonFragmentArgs.fromBundle(arguments!!)
        return  ShareCompat.IntentBuilder.from(activity!!)
                .setText(getString(R.string.share_success_text,args.numCorrect,args.numQuestions))
                .setType("text/plain")
                .intent

    }
    private fun shareSuccess(){
        startActivity(getShareIntent())
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.share->shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }
}
