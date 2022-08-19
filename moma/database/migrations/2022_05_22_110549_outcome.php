<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class Outcome extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        //
        Schema::create('outcomes', function (Blueprint $table) {
            $table->increments("outcome_id");
            $table->string("outcome_name");
            $table->string("outcome_val");
            $table->string("outcome_val_2");
            $table->string("outcome_day");
            $table->string("outcome_month");
            $table->string("outcome_year");
            $table->integer("user_id")->unsigned();
            $table->foreign('user_id')->references('user_id')->on('users');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        //
        Schema::dropIfExists('outcomes');
    }
}
