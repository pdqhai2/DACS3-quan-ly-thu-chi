<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Income extends Model
{
    use HasFactory;

    protected $fillable = [
        'income_id',
        'income_name',
        'income_val',
        'income_val_2',
        'income_valstring',
        'intype_id',
        'income_day',
        'income_month',
        'income_year',
        'user_id',
    ];

    protected $hidden = [];
}
