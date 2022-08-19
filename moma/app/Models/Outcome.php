<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Outcome extends Model
{
    use HasFactory;

    protected $fillable = [
        'outcome_id',
        'outcome_name',
        'outcome_val',
        'outcome_val_2',
        'outcome_valstring',
        'outtype_id',
        'outcome_day',
        'outcome_month',
        'outcome_year',
        'user_id',
    ];

    protected $hidden = [];
}
